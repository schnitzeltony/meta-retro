SUMMARY = "A multi-platform software synthesiser"
DESCRIPTION = "A multi-platform software synthesiser emulating pre-GM MIDI devices such as the Roland MT-32, CM-32L, CM-64 and LAPC-I. In no way endorsed by or affiliated with Roland Corp"
HOMEPAGE = "https://sourceforge.net/projects/munt/"

LICENSE = "LGPL-2.1-or-later & GPL-2.0-or-later & GPL-3.0-or-later"
LIC_FILES_CHKSUM = " \
    file://mt32emu/COPYING.LESSER.txt;md5=243b725d71bb5df4a1e5920b344b86ad \
    file://mt32emu/COPYING.txt;md5=751419260aa954499f7abaabaa882bbe \
    file://mt32emu_alsadrv/COPYING.LESSER.txt;md5=243b725d71bb5df4a1e5920b344b86ad \
    file://mt32emu_alsadrv/COPYING.txt;md5=751419260aa954499f7abaabaa882bbe \
    file://mt32emu_qt/COPYING.txt;md5=d32239bcb673463ab874e80d47fae504 \
    file://mt32emu_smf2wav/COPYING.txt;md5=d32239bcb673463ab874e80d47fae504 \
    file://mt32emu_win32drv/COPYING.LESSER.txt;md5=243b725d71bb5df4a1e5920b344b86ad \
    file://mt32emu_win32drv/COPYING.txt;md5=751419260aa954499f7abaabaa882bbe \
"

DEPENDS = " \
    glib-2.0 \
"

inherit cmake
inherit ${@bb.utils.contains('PACKAGECONFIG', 'qt5', 'cmake_qt5', '', d)}

SRC_URI = "git://github.com/munt/munt.git"
SRCREV = "480985e4b4d774cdf795251fa9f86929295082e6"
PV = "2.5.0"
S = "${WORKDIR}/git"

EXTRA_OECMAKE = " \
    -Dlibmt32emu_SHARED=ON \
"

#PACKAGECONFIG ??= "${@bb.utils.contains_any('BBFILE_COLLECTIONS', 'qt5-layer', 'qt5', '', d)}"
PACKAGECONFIG[qt5] = "-Dmunt_WITH_MT32EMU_QT=ON,-Dmunt_WITH_MT32EMU_QT=OFF,qttools-native qtbase qtmultimedia"

