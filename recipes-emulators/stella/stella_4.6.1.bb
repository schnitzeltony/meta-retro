SUMMARY = "Multi-platform Atari 2600 VCS emulator"
HOMEPAGE = "http://stella.sourceforge.net/" 
SECTION = "emulators"

LICENSE = "GPLv2" 
LIC_FILES_CHKSUM = "file://License.txt;md5=878e3965c7b52d85827c75f5a2f3b314" 

SRC_URI = "${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}/${PV}/${BPN}-${PV}-src.tar.gz \
           file://cross_compile_support.patch"

SRC_URI[md5sum] = "91d7a7333097345e81f90a39fcdcc324"
SRC_URI[sha256sum] = "c1921671dbc08422ae8a7a4102c6a2a34433f04594d67f55a7129c1504bcd288"

DEPENDS = "libsdl2 zlib libpng"

FILES_${PN} += "${datadir}/icons"

inherit autotools-brokensep
