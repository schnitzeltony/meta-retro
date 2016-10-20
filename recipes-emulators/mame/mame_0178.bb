SUMMARY = "Multiple Arcade Machine Emulator"
HOMEPAGE = "http://www.mamedev.org/index.php" 

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=89436197a991695e278e47475b4ff7ae" 

SRC_URI = " \
    https://github.com/mamedev/mame/archive/${BPN}${PV}.tar.gz \
    file://0001-use-pkg-config-for-finding-sdl-library-settings.patch \
"
SRC_URI[md5sum] = "89ee2eeb14a990f4d8aeddf6b835a4d5"
SRC_URI[sha256sum] = "e48df1fbdd8e2a5c3ad87bf3cfa793c619ebbabc2462f5a5569c0f1d29ef0d37"

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
    lua \
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
        mame \
        nltool \
        romcmp \
        split \
        src2html \
        unidasm \
        ; \
    do
        install $binary ${D}${libexecdir}/
    done
}
