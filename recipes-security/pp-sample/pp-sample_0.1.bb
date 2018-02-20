#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

SUMMARY = "Simple helloworld application"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://Makefile"
SRC_URI += "file://${PN}.fc"
SRC_URI += "file://${PN}.if"
SRC_URI += "file://${PN}.te"

FILES_${PN} += " \
	${datadir}/selinux/${POLICY_NAME}/${PN}.pp \
	"

S = "${WORKDIR}"
POLICY_NAME = "targeted"

DEPENDS += "checkpolicy-native policycoreutils-native m4-native semodule-utils-native"
DEPENDS += "refpolicy-purple"

EXTRA_OEMAKE += "BINDIR=${STAGING_BINDIR_NATIVE}"
EXTRA_OEMAKE += "HEADERDIR=${RECIPE_SYSROOT}${datadir}/selinux/${POLICY_NAME}/include"

do_install () {
	install -d ${D}/${datadir}/selinux/${POLICY_NAME}
	install -m 0644 ${S}/${PN}.pp \
		${D}/${datadir}/selinux/${POLICY_NAME}/${PN}.pp
}
