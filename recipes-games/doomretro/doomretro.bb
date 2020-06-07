SUMMARY = "The classic, refined DOOM source port"
HOMEPAGE = "https://www.doomretro.com" 

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=435fa6b85536eff97b3594b76fd0048f" 

DEPENDS = "libsdl2 libsdl2-image libsdl2-mixer"

inherit cmake

SRC_URI = " \
    git://github.com/bradharding/doomretro.git \
    file://doomretro.png \
    file://doomretro.desktop \
"
SRCREV = "2655445b7a82643568bd58192185b25a864e5380"
PV = "3.5.10"
S = "${WORKDIR}/git"

do_install_append() {
    install -d ${D}${datadir}/applications
    install ${WORKDIR}/${BPN}.desktop ${D}${datadir}/applications

    install -d ${D}${datadir}/pixmaps
    install ${WORKDIR}/${BPN}.png ${D}${datadir}/pixmaps/${BPN}.png
}
