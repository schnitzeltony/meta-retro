SUMMARY = "Multi-platform Atari 2600 VCS emulator"
HOMEPAGE = "https://stella-emu.github.io/" 
SECTION = "emulators"

LICENSE = "GPL-2.0-only" 
LIC_FILES_CHKSUM = "file://License.txt;md5=878e3965c7b52d85827c75f5a2f3b314" 

inherit autotools-brokensep pkgconfig gtk-icon-cache

DEPENDS += "libsdl2 zlib libpng"

SRC_URI = " \
    https://github.com/stella-emu/stella/releases/download/${PV}/${BPN}-${PV}-src.tar.xz \
    file://0001-custtomize-configure-and-Makefile-to-our-needs.patch \
"
SRC_URI[sha256sum] = "b49d5e5a5aa872e1f4b6f24eabd72304abdd577801d6ec349760c73b99e7f14d"

CLEANBROKEN = "1"
