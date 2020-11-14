SUMMARY = "OpenTyrian is a port of the DOS shoot-em-up Tyrian"
HOMEPAGE = "https://bitbucket.org/opentyrian/opentyrian/wiki/Home"
SECTION = "games"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "libsdl2 libsdl2-net"

inherit autotools-brokensep pkgconfig

SRC_URI = " \
    git://github.com/opentyrian/opentyrian.git \
    http://camanis.net/opentyrian/tyrian21.zip;name=data \
    file://0001-increase-buffer-size-to-avoid-gcc7-format-overflow-e.patch \
    file://0002-fix-build-with-gcc8.patch \
"
S = "${WORKDIR}/git"
SRCREV = "822c71943f6a06bc2599a0478ea41b6d413648d6"
PV = "2.1.20130907+git${SRCPV}"

SRC_URI[data.md5sum] = "2a3b206a6de25ed4b771af073f8ca904"
SRC_URI[data.sha256sum] = "7790d09a2a3addcd33c66ef063d5900eb81cc9c342f4807eb8356364dd1d9277"

do_install() {
    install -d ${D}${bindir}
    install ${BPN} ${D}${bindir}

    install -d ${D}${mandir}/man6
    install linux/man/${BPN}.6 ${D}${mandir}/man6

    install -d ${D}${datadir}/applications
    install linux/${BPN}.desktop ${D}${datadir}/applications

    install -d ${D}${datadir}/pixmaps
    install linux/icons/tyrian-128.png ${D}${datadir}/pixmaps/${BPN}.png

    install -d ${D}${datadir}/games/tyrian
    install ${WORKDIR}/tyrian21/* ${D}${datadir}/games/tyrian

    # remove unneeded DOS stuff    
    find ${D}${datadir}/games/tyrian -name '*.exe' -exec rm -f {} \; 
    find ${D}${datadir}/games/tyrian -name '*.ovl' -exec rm -f {} \; 
    find ${D}${datadir}/games/tyrian -name 'setup.*' -exec rm -f {} \; 
    find ${D}${datadir}/games/tyrian -name '*.doc' -exec rm -f {} \; 
    find ${D}${datadir}/games/tyrian -name '*.tfp' -exec rm -f {} \; 
}

FILES_${PN} += "${datadir}/games"
