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
SRC_URI[md5sum] = "7110ee24a45a2b4951ad52eb1a3722be"
SRC_URI[sha256sum] = "7077303595bedd7cd0bb94227fa9a6b5609e7c90a3e6523af11bc4afcb0a57cf"

PACKAGECONFIG ??= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl', '', d)} \
"

PACKAGECONFIG[opengl] = "--enable-opengl,--disable-opengl,virtual/libgl libglu"

do_install_append() {
	install -d ${D}/${datadir}/applications
	install -m 0644 ${WORKDIR}/*.desktop ${D}/${datadir}/applications

	install -d ${D}/${datadir}/pixmaps
	install -m 0644 ${WORKDIR}/*.png ${D}/${datadir}/pixmaps
}
