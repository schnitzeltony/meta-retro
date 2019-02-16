SUMMARY = "Multiple Arcade Machine Emulator"
HOMEPAGE = "http://www.mamedev.org/index.php" 

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=798620970c471a3a6b7b5e9c9192fe12"

SRC_URI = " \
    https://github.com/mamedev/mame/archive/${BPN}${PV}.tar.gz \
    file://0001-Show-video-mode-option-accel-in-help-and-GUI.patch \
"
SRC_URI[md5sum] = "b20e4edb93b1e81802b2b23688ad87ca"
SRC_URI[sha256sum] = "588ba357361cc49fdc2754d8343c8b91f6b965b30220a998cbb1da09e49dcbdd"

S = "${WORKDIR}/${BPN}-${BPN}${PV}"

inherit siteinfo

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

# avoid stip fail for size of executable >> 4GB - stolen from chromium
DEBUG_FLAGS_remove_arm = "-g"
DEBUG_FLAGS_append_arm = "-g1"
DEBUG_FLAGS_remove_x86 = "-g"
DEBUG_FLAGS_append_x86 = "-g1"
DEBUG_FLAGS_remove_aarch64 = "-g"
DEBUG_FLAGS_append_aarch64 = "-g1"
DEBUG_FLAGS_remove_x86-64 = "-g"
DEBUG_FLAGS_append_x86-64 = "-g1"

PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"

# TBD: x86 may overrideoverrides later - cannot test
MAME_NOASM="NOASM=1"

# genie.lua does not detect 64bit targets at least on aarch64
MAME_PTR64 = "${@oe.utils.conditional('SITEINFO_BITS', '64', '-DPTR64=1', '', d)}"

EXTRA_OEMAKE = " \
    linux \
    CROSS_BUILD=1 \
    OVERRIDE_CC='${CC} ${MAME_PTR64}' \
    OVERRIDE_CXX='${CXX} ${MAME_PTR64}' \
    TOOLS=1 \
    USE_QTDEBUG=0 \
    LTO=0 \
    NOWERROR=1 \
    ${MAME_NOASM} \
    USE_SYSTEM_LIB_EXPAT=1 \
    USE_SYSTEM_LIB_ZLIB=1 \
    USE_SYSTEM_LIB_FLAC=1 \
    USE_SYSTEM_LIB_JPEG=1 \
    USE_SYSTEM_LIB_LUA=1 \
    USE_SYSTEM_LIB_SQLITE3=1 \
    USE_SYSTEM_LIB_PORTAUDIO=1 \
"

do_compile_prepend() {
    # seems there is some race. Build complains
    # | Assembler messages:
    # | Fatal error: can't create obj/Release/3rdparty/lzma/C/7zAlloc.o: No such file or directory
    # | Assembler messages:
    # | Fatal error: can't create obj/Release/src/mame/drivers/acvirus.o: No such file or directory
    # But that directory is created with a few files
    mkdir -p ${S}/build/projects/sdl/mame/gmake-linux/obj/Release/3rdparty/lzma/C
}

do_install() {
    # Note: Unstripped mame binary for armv7 is > 1GB!!
    install -d ${D}${bindir}

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
        install $binary ${D}${bindir}/
    done
}