SUMMARY = "Versatile Commodore Emulator"
HOMEPAGE = "http://vice-emu.sourceforge.net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=c93c0550bd3173f4504b2cbd8991e50b"

# Sources for c64 software:
# [1] ftp://arnold.c64.org/pub/games/

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/vice-emu/${BPN}-${PV}.tar.gz \
    file://0001-fix-autoreconfig.patch \
    file://c64_16.png \
    file://c64_32.png \
    file://c64_48.png \
    file://vice_64.desktop \
    file://vice_64dtv.desktop \
    file://vice_64sc.desktop \
"
SRC_URI[sha256sum] = "56b978faaeb8b2896032bd604d03c3501002187eef1ca58ceced40f11a65dc0e"

inherit autotools pkgconfig gtk-icon-cache features_check

REQUIRED_DISTRO_FEATURES = "opengl x11"

DEPENDS = " \
    glib-2.0-native \
    bdftopcf-native \
    mkfontdir-native \
    mkfontscale-native \
    dos2unix-native \
    xa-native \
    bison-native \
    glew \
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
    --enable-external-ffmpeg \
    --enable-parsid \
    --enable-native-gtk3ui \
    --without-oss \
    --disable-pdf-docs \
    --libdir=${libdir} \
    --enable-x64 \
"

EXTRA_OECONF:remove = " \
    --disable-static \
"

CONFIGUREOPTS:remove = " \
    --disable-silent-rules \
    ${@append_libtool_sysroot(d)} \
"

export ar_check="no"

do_install:append() {
    install -d ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/vice_64*.desktop ${D}/${datadir}/applications

    for size in 16 32 48; do
        install -d ${D}/${datadir}/icons/hicolor/${size}x${size}/apps
        install -m 0644 ${WORKDIR}/c64_${size}.png ${D}/${datadir}/icons/hicolor/${size}x${size}/apps/c64.png
    done
}

RDEPENDS:${PN} += "hicolor-icon-theme"

RREPLACES:${PN} += "vice-3.2"
RCONFLICTS:${PN} += "vice-3.2"