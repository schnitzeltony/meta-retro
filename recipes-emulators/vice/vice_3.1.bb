SUMMARY = "Versatile Commodore Emulator"
HOMEPAGE = "http://vice-emu.sourceforge.net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=c93c0550bd3173f4504b2cbd8991e50b"

# Sources for c64 software:
# [1] ftp://arnold.c64.org/pub/games/

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/vice-emu/${PN}-${PV}.tar.gz \
    file://0001-fix-autoreconfig.patch \
    file://c64_16.png \
    file://c64_32.png \
    file://c64_48.png \
    file://vice_64.desktop \
"
SRC_URI[md5sum] = "f9018ca464b863da0bb1a82c56374432"
SRC_URI[sha256sum] = "3eb8159633816095006dec36c5c3edd055a87fd8bda193a1194a6801685d1240"

inherit autotools pkgconfig gtk-icon-cache

DEPENDS = " \
    bdftopcf-native \
    mkfontdir-native \
    mkfontscale-native \
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

PACKAGECONFIG[pulseaudio] = "--with-pulse,--without-pulse,pulseaudio,pulseaudio-server"

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
