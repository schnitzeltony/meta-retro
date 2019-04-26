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
SRC_URI[md5sum] = "496dc9837f6bd634a7567e37f21a49f2"
SRC_URI[sha256sum] = "17aa8c5f08e09c51dd7dd933f93b0a1929d2832bd66f3bd994fa50ebb2b9a2b1"

CLEANBROKEN = "1"
