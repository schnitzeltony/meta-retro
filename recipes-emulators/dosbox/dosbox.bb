SUMMARY = "DOSBox is a DOS-emulator that uses the SDL-library"
HOMEPAGE = "http://www.dosbox.com/" 

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f" 

DEPENDS = "libsdl libsdl-net libpng"

inherit autotools pkgconfig

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}/${PV}/${BPN}-${PV}.tar.gz \
    file://dosbox.desktop \
    file://dosbox.png \
    file://0001-use-pkgconfig-to-find-sdl.patch \
"
SRC_URI[sha256sum] = "c0d13dd7ed2ed363b68de615475781e891cd582e8162b5c3669137502222260a"
PV = "0.74-3"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'opengl', d)}"
PACKAGECONFIG[opengl] = "--enable-opengl,--disable-opengl,virtual/libgl libglu"

do_install_append() {
	install -d ${D}/${datadir}/applications
	install -m 0644 ${WORKDIR}/*.desktop ${D}/${datadir}/applications

	install -d ${D}/${datadir}/pixmaps
	install -m 0644 ${WORKDIR}/*.png ${D}/${datadir}/pixmaps
}
