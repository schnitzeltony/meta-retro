SUMMARY = "Versatile Commodore Emulator"
HOMEPAGE = "http://vice-emu.sourceforge.net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=c93c0550bd3173f4504b2cbd8991e50b"

# Sources for c64 software:
# [1] ftp://arnold.c64.org/pub/games/

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/vice-emu/${BPN}-${PV}.tar.gz \
    file://0001-fix-autoreconfig.patch \
    file://0002-Do-not-check-if-ar-suppurts-u-option.patch \
    file://c64_16.png \
    file://c64_32.png \
    file://c64_48.png \
    file://vice_64.desktop \
"
SRC_URI[md5sum] = "b0797f534b33f638220418207d606cf5"
SRC_URI[sha256sum] = "1a55b38cc988165b077808c07c52a779d181270b28c14b5c9abf4e569137431d"

inherit autotools pkgconfig gtk-icon-cache

DEPENDS = " \
    glib-2.0-native \
    bdftopcf-native \
    mkfontdir-native \
    mkfontscale-native \
    xa-native \
    bison-native \
    gtk+3 \
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
"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'pulseaudio', d)}"
PACKAGECONFIG[pulseaudio] = "--with-pulse,--without-pulse,pulseaudio,pulseaudio-server"

EXTRA_OECONF = " \
    --disable-option-checking \
    --enable-external-ffmpeg \
    --enable-parsid \
    --enable-fullscreen \
    --enable-gnomeui \
    --with-uithreads \
    --without-oss \
    --libdir=${libdir} \
"

do_install_append() {
    install -d ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/vice_64.desktop ${D}/${datadir}/applications

    for size in 16 32 48; do
        install -d ${D}/${datadir}/icons/hicolor/${size}x${size}/apps
        install -m 0644 ${WORKDIR}/c64_${size}.png ${D}/${datadir}/icons/hicolor/${size}x${size}/apps/c64.png
    done
}
