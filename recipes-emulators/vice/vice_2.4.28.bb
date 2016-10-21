SUMMARY = "Versatile Commodore Emulator"
HOMEPAGE = "http://vice-emu.sourceforge.net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=c93c0550bd3173f4504b2cbd8991e50b"

# Sources for c64 software:
# [1] ftp://arnold.c64.org/pub/games/

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/vice-emu/${PN}-${PV}.tar.gz \
    file://0001-fix-autoreconfig.patch \
    file://0002-Do-not-build-os2dialogs-it-causes-infinite-loop.patch \
    file://0003-ffmpegdrv.c-fix-build-with-current-ffmpeg.patch \
    file://0004-joy.h-fix-typo.patch \
    file://0005-fix-sdl-build.patch \
    file://vice_64.desktop \
"
SRC_URI[md5sum] = "533554f5d7680b85c0954e4d4811274a"
SRC_URI[sha256sum] = "1d73ba3761b161aa76a31a9b8c6550c998272fa2feff19451d870120b14a1838"

inherit autotools pkgconfig

DEPENDS = "gtk+ pulseaudio libav libsdl libpng jpeg giflib libxxf86vm portaudio-v19 mpg123 virtual/libgl vte9"

EXTRA_OECONF = " \
    --disable-option-checking \
    --enable-external-ffmpeg \
    --enable-parsid \
    --enable-gnomeui \
"

do_install_append() {
    install -d ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/vice_64.desktop ${D}/${datadir}/applications
}

FILES_${PN} += "${datadir}/icons"
