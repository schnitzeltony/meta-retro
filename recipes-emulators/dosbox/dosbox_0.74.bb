SUMMARY = "OSBox is a DOS-emulator that uses the SDL-library"
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
    file://0002-include-dos_inc.h-add-missing-include.patch \
"
SRC_URI[md5sum] = "b9b240fa87104421962d14eee71351e8"
SRC_URI[sha256sum] = "13f74916e2d4002bad1978e55727f302ff6df3d9be2f9b0e271501bd0a938e05"

PACKAGECONFIG ??= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl', '', d)} \
"

PACKAGECONFIG[opengl] = "--enable-opengl,--disable-opengl,virtual/libgl"

do_install_append() {
	install -d ${D}/${datadir}/applications
	install -m 0644 ${WORKDIR}/*.desktop ${D}/${datadir}/applications

	install -d ${D}/${datadir}/pixmaps
	install -m 0644 ${WORKDIR}/*.png ${D}/${datadir}/pixmaps
}
