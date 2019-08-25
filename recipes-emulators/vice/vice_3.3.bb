SUMMARY = "Versatile Commodore Emulator"
HOMEPAGE = "http://vice-emu.sourceforge.net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=c93c0550bd3173f4504b2cbd8991e50b"

# Sources for c64 software:
# [1] ftp://arnold.c64.org/pub/games/

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/vice-emu/${BPN}-${PV}.tar.gz \
    file://0001-fix-autoreconfig.patch \
    file://0002-Try-to-port-shaders-to-OpenGL-2.patch \
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
    libav \
    libpng \
    jpeg \
    giflib \
    libxxf86vm \
    portaudio-v19 \
    mpg123 \
    virtual/libgl \
    vte9 \
"

PACKAGECONFIG ??= "gtk3 glew ${@bb.utils.filter('DISTRO_FEATURES', 'pulseaudio', d)}"
PACKAGECONFIG[pulseaudio] = "--with-pulse,--without-pulse,pulseaudio,pulseaudio-server"

# either gtk3 or sdl2 for gui
PACKAGECONFIG[gtk3] = "--enable-native-gtk3ui,--disable-native-gtk3ui,gtk+3 pango"
# together with gtk3 / requires OpenGL3
PACKAGECONFIG[glew] = ",,glew"

PACKAGECONFIG[sdl2] = "--enable-sdlui2,--disable-sdlui2,libsdl2"


EXTRA_OECONF = " \
    --disable-option-checking \
    --enable-external-ffmpeg \
    --enable-parsid \
    --enable-fullscreen \
    --with-uithreads \
    --without-oss \
    --libdir=${libdir} \
"

export ar_check="no"

do_install_append() {
    install -d ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/vice_64.desktop ${D}/${datadir}/applications

    for size in 16 32 48; do
        install -d ${D}/${datadir}/icons/hicolor/${size}x${size}/apps
        install -m 0644 ${WORKDIR}/c64_${size}.png ${D}/${datadir}/icons/hicolor/${size}x${size}/apps/c64.png
    done
}

RDEPENDS_${PN} += "hicolor-icon-theme"
