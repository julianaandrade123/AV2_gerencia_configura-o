#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup batch script, version 3.3.4
#
# Optional ENV vars
# -----------------
#   JAVA_HOME - location of a JDK home dir, required when download maven via java source
#   MVNW_REPOURL - repo url base for downloading maven distribution
#   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
#   MVNW_VERBOSE - true: enable verbose log; debug: trace the mvnw script; others: silence output
# ----------------------------------------------------------------------------

set -eu

[ "${MVNW_VERBOSE-}" != debug ] || set -x

# OS detection
native_path() { printf %s\\n "$1"; }

case "$(uname)" in
CYGWIN* | MINGW*)
  [ -z "${JAVA_HOME-}" ] || JAVA_HOME="$(cygpath --unix "$JAVA_HOME")"
  native_path() { cygpath --path --windows "$1"; }
  ;;
esac

# Set java home
set_java_home() {
  if [ -n "${JAVA_HOME-}" ]; then
    if [ -x "$JAVA_HOME/jre/sh/java" ]; then
      JAVACMD="$JAVA_HOME/jre/sh/java"
      JAVACCMD="$JAVA_HOME/jre/sh/javac"
    else
      JAVACMD="$JAVA_HOME/bin/java"
      JAVACCMD="$JAVA_HOME/bin/javac"
      if [ ! -x "$JAVACMD" ] || [ ! -x "$JAVACCMD" ]; then
        echo "The JAVA_HOME environment variable is not defined correctly." >&2
        return 1
      fi
    fi
  else
    JAVACMD="$(command -v java || :)" || :
    JAVACCMD="$(command -v javac || :)" || :
    if [ ! -x "${JAVACMD-}" ] || [ ! -x "${JAVACCMD-}" ]; then
      echo "java/javac not found and JAVA_HOME is not set." >&2
      return 1
    fi
  fi
}

# hash like Java String::hashCode
hash_string() {
  str="${1:-}" h=0
  while [ -n "$str" ]; do
    char="${str%"${str#?}"}"
    h=$(((h * 31 + $(LC_CTYPE=C printf %d "'$char")) % 4294967296))
    str="${str#?}"
  done
  printf %x\\n $h
}

verbose() { :; }
[ "${MVNW_VERBOSE-}" != true ] || verbose() { printf %s\\n "$1"; }

die() { printf %s\\n "$1" >&2; exit 1; }

trim() { printf "%s" "$1" | tr -d '[:space:]'; }

scriptDir="$(dirname "$0")"
scriptName="$(basename "$0")"

# Load properties
while IFS="=" read -r key value; do
  case "$key" in
  distributionUrl) distributionUrl=$(trim "$value") ;;
  distributionSha256Sum) distributionSha256Sum=$(trim "$value") ;;
  esac
done <"$scriptDir/.mvn/wrapper/maven-wrapper.properties"

[ -n "${distributionUrl-}" ] || die "distributionUrl missing in maven-wrapper.properties"

case "${distributionUrl##*/}" in
maven-mvnd-*bin.*)
  MVN_CMD=mvnd.sh
  _MVNW_REPO_PATTERN=/maven/mvnd/
  case "${PROCESSOR_ARCHITECTURE-}${PROCESSOR_ARCHITEW6432-}:$(uname -a)" in
  *AMD64:CYGWIN* | *AMD64:MINGW*) distributionPlatform=windows-amd64 ;;
  :Darwin*x86_64) distributionPlatform=darwin-amd64 ;;
  :Darwin*arm64) distributionPlatform=darwin-aarch64 ;;
  :Linux*x86_64*) distributionPlatform=linux-amd64 ;;
  *)
    echo "Unknown platform, using Linux AMD64." >&2
    distributionPlatform=linux-amd64
    ;;
  esac
  distributionUrl="${distributionUrl%-bin.*}-$distributionPlatform.zip"
  ;;
maven-mvnd-*)
  MVN_CMD=mvnd.sh
  _MVNW_REPO_PATTERN=/maven/mvnd/
  ;;
*)
  MVN_CMD="mvn${scriptName#mvnw}"
  _MVNW_REPO_PATTERN=/org/apache/maven/
  ;;
esac

[ -z "${MVNW_REPOURL-}" ] || distributionUrl="$MVNW_REPOURL$_MVNW_REPO_PATTERN${distributionUrl#*"$_MVNW_REPO_PATTERN"}"

distributionUrlName="${distributionUrl##*/}"
distributionUrlNameMain="${distributionUrlName%.*}"
distributionUrlNameMain="${distributionUrlNameMain%-bin}"

MAVEN_USER_HOME="${MAVEN_USER_HOME:-$HOME/.m2}"
MAVEN_HOME="${MAVEN_USER_HOME}/wrapper/dists/${distributionUrlNameMain}/$(hash_string "$distributionUrl")"

exec_maven() {
  unset MVNW_VERBOSE MVNW_USERNAME MVNW_PASSWORD MVNW_REPOURL || :
  exec "$MAVEN_HOME/bin/$MVN_CMD" "$@" || die "cannot exec Maven"
}

# If exists, run
if [ -d "$MAVEN_HOME" ]; then
  verbose "Found Maven in $MAVEN_HOME"
  exec_maven "$@"
fi

# Must end with -bin.zip
case "$distributionUrl" in
*?-bin.zip | *?maven-mvnd-?*-?*.zip) ;;
*) die "Invalid distributionUrl: must end with -bin.zip" ;;
esac

# temp dir
TMP_DOWNLOAD_DIR="$(mktemp -d)" || die "failed temp dir"
trap 'rm -rf "$TMP_DOWNLOAD_DIR"' HUP INT TERM EXIT

mkdir -p "${MAVEN_HOME%/*}"

verbose "Downloading Maven from $distributionUrl"

if ! command -v unzip >/dev/null; then
  distributionUrl="${distributionUrl%.zip}.tar.gz"
  distributionUrlName="${distributionUrl##*/}"
fi

__MVNW_QUIET_WGET=--quiet __MVNW_QUIET_CURL=--silent __MVNW_QUIET_UNZIP=-q __MVNW_QUIET_TAR=''
[ "${MVNW_VERBOSE-}" != true ] || __MVNW_QUIET_WGET='' __MVNW_QUIET_CURL='' __MVNW_QUIET_UNZIP='' __MVNW_QUIET_TAR=v

case "${MVNW_PASSWORD:+has-password}" in
'') MVNW_USERNAME='' MVNW_PASSWORD='' ;;
has-password) [ -n "${MVNW_USERNAME-}" ] || MVNW_PASSWORD='' ;;
esac

if [ -z "${MVNW_USERNAME-}" ] && command -v wget >/dev/null; then
  wget ${__MVNW_QUIET_WGET:+$__MVNW_QUIET_WGET} "$distributionUrl" -O "$TMP_DOWNLOAD_DIR/$distributionUrlName" \
    || die "wget failed"
elif [ -z "${MVNW_USERNAME-}" ] && command -v curl >/dev/null; then
  curl ${__MVNW_QUIET_CURL:+$__MVNW_QUIET_CURL} -f -L -o "$TMP_DOWNLOAD_DIR/$distributionUrlName" "$distributionUrl" \
    || die "curl failed"
elif set_java_home; then
  javaSource="$TMP_DOWNLOAD_DIR/Downloader.java"
  targetZip="$TMP_DOWNLOAD_DIR/$distributionUrlName"
  cat >"$javaSource" <<'EOF'
public class Downloader extends java.net.Authenticator {
  protected java.net.PasswordAuthentication getPasswordAuthentication() {
    return new java.net.PasswordAuthentication(
      System.getenv("MVNW_USERNAME"), System.getenv("MVNW_PASSWORD").toCharArray());
  }
  public static void main(String[] args) throws Exception {
    setDefault(new Downloader());
    java.nio.file.Files.copy(
      java.net.URI.create(args[0]).toURL().openStream(),
      java.nio.file.Paths.get(args[1]).toAbsolutePath().normalize());
  }
}
EOF
  "$(native_path "$JAVACCMD")" "$(native_path "$javaSource")" || die "javac failed"
  "$(native_path "$JAVACMD")" -cp "$(native_path "$TMP_DOWNLOAD_DIR")" Downloader \
    "$distributionUrl" "$(native_path "$targetZip")"
fi

# SHA256
if [ -n "${distributionSha256Sum-}" ]; then
  distributionSha256Result=false
  if command -v sha256sum >/dev/null; then
    echo "$distributionSha256Sum  $TMP_DOWNLOAD_DIR/$distributionUrlName" | sha256sum -c >/dev/null && distributionSha256Result=true
  elif command -v shasum >/dev/null; then
    echo "$distributionSha256Sum  $TMP_DOWNLOAD_DIR/$distributionUrlName" | shasum -a 256 -c >/dev/null && distributionSha256Result=true
  else
    die "Checksum required but sha256sum/shasum missing"
  fi

  [ "$distributionSha256Result" = true ] || die "Checksum verification failed!"
fi

# extract
if command -v unzip >/dev/null; then
  unzip ${__MVNW_QUIET_UNZIP:+$__MVNW_QUIET_UNZIP} "$TMP_DOWNLOAD_DIR/$distributionUrlName" -d "$TMP_DOWNLOAD_DIR" \
    || die "unzip failed"
else
  tar xzf${__MVNW_QUIET_TAR:+$__MVNW_QUIET_TAR} "$TMP_DOWNLOAD_DIR/$distributionUrlName" -C "$TMP_DOWNLOAD_DIR" \
    || die "untar failed"
fi

# Find directory
actualDistributionDir=""
if [ -d "$TMP_DOWNLOAD_DIR/$distributionUrlNameMain" ] && \
   [ -f "$TMP_DOWNLOAD_DIR/$distributionUrlNameMain/bin/$MVN_CMD" ]; then
  actualDistributionDir="$distributionUrlNameMain"
else
  for dir in "$TMP_DOWNLOAD_DIR"/*; do
    if [ -f "$dir/bin/$MVN_CMD" ]; then
      actualDistributionDir="$(basename "$dir")"
      break
    fi
  done
fi

[ -n "$actualDistributionDir" ] || die "Failed to locate extracted Maven distribution"

printf %s\\n "$distributionUrl" >"$TMP_DOWNLOAD_DIR/$actualDistributionDir/mvnw.url"
mv "$TMP_DOWNLOAD_DIR/$actualDistributionDir" "$MAVEN_HOME" \
  || die "Failed to install Maven"

exec_maven "$@"
