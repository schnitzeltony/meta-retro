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
SRC_URI[md5sum] = "9df5fd3af9720115359ebf0171ed36e6"
SRC_URI[sha256sum] = "d611f98bb35ed9eacfb63826b12b4e8b67b874a2a87019d4bdaf1e7f4724c40d"

CLEANBROKEN = "1"
