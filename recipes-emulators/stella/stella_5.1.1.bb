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
SRC_URI[md5sum] = "ec100fc3a21529a9da21b907dc0d4114"
SRC_URI[sha256sum] = "3de6ad86e4e881d1a23395c36c5453eb8a1160d0f3a021992fe990a009a933da"
S = "${WORKDIR}/${BPN}-${PV}-src"

CLEANBROKEN = "1"

FILES_${PN} += "${datadir}/icons"
