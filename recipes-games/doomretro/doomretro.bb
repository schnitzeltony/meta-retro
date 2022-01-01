SUMMARY = "The classic, refined DOOM source port"
HOMEPAGE = "https://www.doomretro.com" 

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=435fa6b85536eff97b3594b76fd0048f" 

DEPENDS = "libsdl2 libsdl2-image libsdl2-mixer"

inherit cmake pkgconfig

SRC_URI = " \
    git://github.com/bradharding/doomretro.git;branch=master;protocol=https \
    file://doomretro.png \
    file://doomretro.desktop \
"
SRCREV = "bdcad451c02ffc38ac44900d32648952ad710971"
PV = "4.4.1"
S = "${WORKDIR}/git"

do_install:append() {
    install -d ${D}${datadir}/applications
    install ${WORKDIR}/${BPN}.desktop ${D}${datadir}/applications

    install -d ${D}${datadir}/pixmaps
    install ${WORKDIR}/${BPN}.png ${D}${datadir}/pixmaps/${BPN}.png
}
