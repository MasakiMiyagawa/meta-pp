#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

SUMMARY = "Simple helloworld application"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://Makefile"
SRC_URI += "file://pp-sample.fc"
SRC_URI += "file://pp-sample.if"
SRC_URI += "file://pp-sample.te"

S = "${WORKDIR}"

DEPENDS += "checkpolicy-native policycoreutils-native m4-native semodule-utils-native"
DEPENDS += "refpolicy-purple"

EXTRA_OEMAKE += "BINDIR=${STAGING_BINDIR_NATIVE}"
EXTRA_OEMAKE += "HEADERDIR=${RECIPE_SYSROOT}${datadir}/selinux/targeted/include"
