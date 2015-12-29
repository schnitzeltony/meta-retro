SUMMARY = "Multiple Arcade Machine Emulator"
HOMEPAGE = "http://www.mamedev.org/index.php" 

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README.md;beginline=22;endline=29;md5=4e58445eb710431608ce9127a9462da1" 

SRC_URI = " \
    https://github.com/mamedev/mame/archive/${BPN}${PV}.tar.gz \
    file://0001-use-pkg-config-for-finding-sdl-library-settings.patch \
"
SRC_URI[md5sum] = "227a506592308e004dbd55a4dc3b51d7"
SRC_URI[sha256sum] = "3b6db52ddffed867ae171664e327f0b2bade64139d3450dc7166c4f90b6d94e8"

S = "${WORKDIR}/${BPN}-${BPN}${PV}"

# TBD
# * move to libsdl2 libsdl2-ttf

DEPENDS = " \
    libsdl \
    libsdl-ttf \
    fontconfig \
    libxinerama \
    \
    expat \
    zlib \
    flac \
    jpeg \
    lua lua-native \
    sqlite3 \
"

EXTRA_OEMAKE += " \
    linux \
    CROSS_BUILD=1 \
    NOASM=1 \
    OVERRIDE_CC='${CC}' \
    OVERRIDE_CXX='${CXX}' \
    SDL_LIBVER=sdl \
    TOOLS=1 \
    USE_QTDEBUG=0 \
    NOWERROR=1 \
    USE_SYSTEM_LIB_EXPAT=1 \
    USE_SYSTEM_LIB_ZLIB=1 \
    USE_SYSTEM_LIB_FLAC=1 \
    USE_SYSTEM_LIB_JPEG=1 \
    USE_SYSTEM_LIB_LUA=1 \
    USE_SYSTEM_LIB_SQLITE3=1 \
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
