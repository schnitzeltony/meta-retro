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
    file://0003-joy.h-fix-typo.patch \
    file://0004-fix-sdl-build.patch \
    file://c64_16.png \
    file://c64_32.png \
    file://c64_48.png \
    file://vice_64.desktop \
"
SRC_URI[md5sum] = "e4dd02494c38610718df9f4d4566a0c7"
SRC_URI[sha256sum] = "111bb26310bd660802767084f6840a75156158134689a23a3c8a0e0d817ee36d"

inherit autotools pkgconfig gtk-icon-cache

DEPENDS = " \
    gtk+ \
    libav \
    libsdl \
    libpng \
    jpeg \
    giflib \
    libxxf86vm \
    portaudio-v19 \
    mpg123 \
    virtual/libgl \
    vte9 \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11 opengl", "gtkglext", "", d)} \
"

PACKAGECONFIG[pulseaudio] = "--with-pulse,--without-pulse,pulseaudio"

EXTRA_OECONF = " \
    --disable-option-checking \
    --enable-external-ffmpeg \
    --enable-parsid \
    --enable-fullscreen \
    --enable-gnomeui \
    --with-uithreads \
    --without-oss \
"

do_install_append() {
    install -d ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/vice_64.desktop ${D}/${datadir}/applications

    for size in 16 32 48; do
        install -d ${D}/${datadir}/icons/hicolor/${size}x${size}/apps
        install -m 0644 ${WORKDIR}/c64_${size}.png ${D}/${datadir}/icons/hicolor/${size}x${size}/apps/c64.png
    done
}

FILES_${PN} += "${datadir}/icons"
