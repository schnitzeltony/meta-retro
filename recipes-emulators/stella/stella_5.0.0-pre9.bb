SUMMARY = "Multi-platform Atari 2600 VCS emulator"
HOMEPAGE = "http://stella.sourceforge.net/" 
SECTION = "emulators"

LICENSE = "GPLv2" 
LIC_FILES_CHKSUM = "file://License.txt;md5=878e3965c7b52d85827c75f5a2f3b314" 

SRC_URI = " \
    https://github.com/stella-emu/${BPN}/archive/${PV}.tar.gz \
    file://0001-custtomize-configure-and-Makefile-to-our-needs.patch \
"

SRC_URI[md5sum] = "242171e6ed7e0db77a24f85e170f92e5"
SRC_URI[sha256sum] = "5f1843a5531d0221498bae51fb458d39eff14aa4272a1bd0609f5969219cf78e"

FILES_${PN} += "${datadir}/icons"

inherit autotools-brokensep gtk-icon-cache

DEPENDS += "libsdl2 zlib libpng"

CLEANBROKEN = "1"
