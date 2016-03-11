SUMMARY = "Multiple Arcade Machine Emulator"
HOMEPAGE = "http://www.mamedev.org/index.php" 

LICENSE = "MAME"
LIC_FILES_CHKSUM = "file://docs/mamelicense.txt;md5=f40fdf5d5756f220c8e44004f2ef48dc" 

SRC_URI = " \
    https://github.com/mamedev/mame/archive/${BPN}${PV}.tar.gz \
    file://0001-use-pkg-config-for-finding-sdl-library-settings.patch \
    file://0002-float4_neon.h-refactor-buildins-for-later-gcc-s.patch \
"
SRC_URI[md5sum] = "4b21d52df5f826de756a4c80975bd414"
SRC_URI[sha256sum] = "e543316e238b02ae80f8de6e1da3eaaac3754bc8370deb7c31a4bc73121763c5"

S = "${WORKDIR}/${BPN}-${BPN}${PV}"

DEPENDS = " \
    libsdl2 \
    libsdl2-ttf \
    fontconfig \
    libxinerama \
    \
    expat \
    zlib \
    flac \
    jpeg \
    lua lua-native \
    sqlite3 \
    portaudio-v19 \
"

CLEANBROKEN = "1"

EXTRA_OEMAKE = " \
    linux \
    CROSS_BUILD=1 \
    OVERRIDE_CC='${CC}' \
    OVERRIDE_CXX='${CXX}' \
    TOOLS=1 \
    USE_QTDEBUG=0 \
    NOWERROR=1 \
    USE_SYSTEM_LIB_EXPAT=1 \
    USE_SYSTEM_LIB_ZLIB=1 \
    USE_SYSTEM_LIB_FLAC=1 \
    USE_SYSTEM_LIB_JPEG=1 \
    USE_SYSTEM_LIB_LUA=1 \
    USE_SYSTEM_LIB_SQLITE3=1 \
    USE_SYSTEM_LIB_PORTAUDIO=1 \
"

EXTRA_OEMAKE_append_arm = " \
    NOASM=1 \
"

do_install() {
    # Note: Unstripped mame binary for armv7 is > 1GB!!
    install -d ${D}${bindir}
    install mame ${D}${bindir}/

    install -d ${D}${libexecdir}
    for binary in \
        castool \
        chdman \
        floptool \
        imgtool \
        jedutil \
        ldresample \
        ldverify \
        nltool \
        nlwav \
        pngcmp \
        split \
        srcclean \
        testkeys \
        unidasm \
        ; \
    do
        install $binary ${D}${libexecdir}/
    done
}
