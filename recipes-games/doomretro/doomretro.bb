SUMMARY = "The classic, refined DOOM source port"
HOMEPAGE = "https://www.doomretro.com" 

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=435fa6b85536eff97b3594b76fd0048f" 

DEPENDS = "libsdl2 libsdl2-image libsdl2-mixer"

inherit cmake pkgconfig

SRC_URI = " \
    git://github.com/bradharding/doomretro.git \
    file://doomretro.png \
    file://doomretro.desktop \
"
SRCREV = "0ba43d17bbe925a42eebfa09b794cd6a437cb099"
PV = "4.3"
S = "${WORKDIR}/git"

do_install:append() {
    install -d ${D}${datadir}/applications
    install ${WORKDIR}/${BPN}.desktop ${D}${datadir}/applications

    install -d ${D}${datadir}/pixmaps
    install ${WORKDIR}/${BPN}.png ${D}${datadir}/pixmaps/${BPN}.png
}
