SUMMARY = "Multi-platform Atari 2600 VCS emulator"
HOMEPAGE = "https://stella-emu.github.io/" 
SECTION = "emulators"

LICENSE = "GPLv2" 
LIC_FILES_CHKSUM = "file://License.txt;md5=878e3965c7b52d85827c75f5a2f3b314" 

inherit autotools-brokensep gtk-icon-cache

DEPENDS += "libsdl2 zlib libpng"

SRC_URI = " \
    https://github.com/stella-emu/stella/releases/download/${PV}/${BPN}-${PV}-src.tar.xz \
    file://0001-custtomize-configure-and-Makefile-to-our-needs.patch \
"
SRC_URI[md5sum] = "f7cccdf4761b7183a235ffeef136e180"
SRC_URI[sha256sum] = "e074317c25e5d4cabec4558909d301c3a7654ad620863f05d342244fe6bdfe0a"

CLEANBROKEN = "1"

FILES_${PN} += "${datadir}/icons"
