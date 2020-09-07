SUMMARY = "OpenTyrian is a port of the DOS shoot-em-up Tyrian"
HOMEPAGE = "https://bitbucket.org/opentyrian/opentyrian/wiki/Home"
SECTION = "games"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "libsdl libsdl-net"

inherit autotools-brokensep pkgconfig

SRC_URI = " \
    http://www.camanis.net/opentyrian/releases/${BPN}-${PV}-src.tar.gz \
    http://camanis.net/opentyrian/tyrian21.zip;name=data \
    file://0001-Makefile-use-pkgconfig-to-find-sdl.patch \
    file://0002-Fix-several-uninitialized-variable-warnings.patch \
    file://0003-fix-build-with-pedantic-gcc6.patch \
    file://0004-increase-buffer-size-to-avoid-gcc7-format-overflow-e.patch \
    file://0005-fix-build-with-gcc8.patch \
    file://0006-Fix-build-with-gcc-9.patch \
    file://0007-Move-definitions-that-don-t-need-to-be-exposed-from-.patch \
"
SRC_URI[md5sum] = "978686c5a1dfbac69a2161aadf084c2b"
SRC_URI[sha256sum] = "f54b6b3cedcefa187c9f605d6164aae29ec46a731a6df30d351af4c008dee45f"

SRC_URI[data.md5sum] = "2a3b206a6de25ed4b771af073f8ca904"
SRC_URI[data.sha256sum] = "7790d09a2a3addcd33c66ef063d5900eb81cc9c342f4807eb8356364dd1d9277"

# Hack to build - yes this is bad but I sometimes still like to play
# opentyrian at the risk it is going to kill me
CFLAGS += "-Wimplicit-fallthrough=0 -Wno-error=format-truncation -Wno-error=format-overflow"

do_install() {
    install -d ${D}${bindir}
    install ${BPN} ${D}${bindir}

    install -d ${D}${mandir}/man6
    install linux/man/${BPN}.6 ${D}${mandir}/man6

    install -d ${D}${datadir}/applications
    install linux/${BPN}.desktop ${D}${datadir}/applications

    install -d ${D}${datadir}/pixmaps
    install linux/icons/tyrian-128.png ${D}${datadir}/pixmaps/${BPN}.png

    install -d ${D}${datadir}/${BPN}/data
    install ${WORKDIR}/tyrian21/* ${D}${datadir}/${BPN}/data

    # remove unneeded DOS stuff    
    find ${D}${datadir}/${BPN}/data -name '*.exe' -exec rm -f {} \; 
    find ${D}${datadir}/${BPN}/data -name '*.ovl' -exec rm -f {} \; 
    find ${D}${datadir}/${BPN}/data -name 'setup.*' -exec rm -f {} \; 
    find ${D}${datadir}/${BPN}/data -name '*.doc' -exec rm -f {} \; 
    find ${D}${datadir}/${BPN}/data -name '*.tfp' -exec rm -f {} \; 
}
