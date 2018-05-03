inherit systemd
RRECOMMENDS_${PN} += "kernel-module-xt-state kernel-module-xt-tcpudp"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://ip4tables.rules.org \
	    file://iptables-start.sh \
	    file://iptables-end.sh \
	    file://iptables.service \
	   "

FILES_${PN} += " ${sysconfdir}/iptables/*"
SYSTEMD_SERVICE_${PN} += "iptables.service"

do_install_append () {
	install -d ${D}${sysconfdir}/iptables
	install -m 0444 ${WORKDIR}/ip4tables.rules.org \
		${D}${sysconfdir}/iptables/
	install -m 0755 ${WORKDIR}/iptables-*.sh \
		${D}${sysconfdir}/iptables/
    	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)};
		then
		install -d ${D}${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/iptables.service \
		${D}${systemd_unitdir}/system/
	fi
}
