SUMMARY = "Multi-platform Atari 2600 VCS emulator"
HOMEPAGE = "http://stella.sourceforge.net/" 
SECTION = "emulators"

LICENSE = "GPLv2" 
LIC_FILES_CHKSUM = "file://License.txt;md5=878e3965c7b52d85827c75f5a2f3b314" 

SRC_URI = "https://github.com/stella-emu/stella/releases/download/release-${PV}/${BPN}-${PV}-src.tar.xz \
           file://cross_compile_support.patch"

SRC_URI[md5sum] = "898578ee405430815e12374f191e9b51"
SRC_URI[sha256sum] = "93a75d1b343b1e66b6dc526c0f9d8a0c3678d346033f7cdfe76dc93f14d956ad"

FILES_${PN} += "${datadir}/icons"

inherit autotools-brokensep gtk-icon-cache

DEPENDS += "libsdl2 zlib libpng"

CLEANBROKEN = "1"
