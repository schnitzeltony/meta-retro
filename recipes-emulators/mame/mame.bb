SUMMARY = "Multiple Arcade Machine Emulator"
HOMEPAGE = "http://www.mamedev.org/index.php" 

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=cdeccd9fdcfe3e35c2c2be5cdf8d37d6"

SRC_URI = " \
    git://github.com/mamedev/mame.git;branch=master;protocol=https \
    file://mame.desktop \
    file://mame32k.desktop \
"
PV = "0242"
SRCREV = "e8166b52740880cedfee0b779eb71c5994ff0334"
S = "${WORKDIR}/git"

inherit pkgconfig siteinfo gtk-icon-cache

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
    glm \
    sqlite3 \
    portaudio-v19 \
    rapidjson \
    portmidi \
"

CLEANBROKEN = "1"

# avoid stip fail for size of executable >> 4GB - stolen from chromium
DEBUG_FLAGS:remove:arm = "-g"
DEBUG_FLAGS:append:arm = "-g1"
DEBUG_FLAGS:remove:x86 = "-g"
DEBUG_FLAGS:append:x86 = "-g1"
DEBUG_FLAGS:remove:aarch64 = "-g"
DEBUG_FLAGS:append:aarch64 = "-g1"
DEBUG_FLAGS:remove:x86-64 = "-g"
DEBUG_FLAGS:append:x86-64 = "-g1"

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
    OPTIMIZE=3 \
    TOOLS=1 \
    USE_QTDEBUG=0 \
    LTO=0 \
    NOWERROR=1 \
    ${MAME_NOASM} \
    USE_SYSTEM_LIB_EXPAT=1 \
    USE_SYSTEM_LIB_FLAC=1 \
    USE_SYSTEM_LIB_JPEG=1 \
    USE_SYSTEM_LIB_GLM=1 \
    USE_SYSTEM_LIB_PORTAUDIO=1 \
    USE_SYSTEM_LIB_SQLITE3=1 \
    USE_SYSTEM_LIB_ZLIB=1 \
    USE_SYSTEM_LIB_RAPIDJSON=1 \
    USE_OPENMP=1 \
    USE_SYSTEM_LIB_PORTMIDI=1 \
    SDL_INI_PATH=${sysconfdir}/${BPN} \
    PYTHON_EXECUTABLE=python3 \
"

do_compile:prepend() {
    # seems there is some race. Build complains
    # | Assembler messages:
    # | Fatal error: can't create obj/Release/3rdparty/lzma/C/7zAlloc.o: No such file or directory
    # | Assembler messages:
    # | Fatal error: can't create obj/Release/src/mame/drivers/acvirus.o: No such file or directory
    # But that directory is created with a few files
    mkdir -p ${S}/build/projects/sdl/mame/gmake-linux/obj/Release/3rdparty/lzma/C
}

do_install() {
    # stolen much from https://src.fedoraproject.org/cgit/rpms/mame.git/tree/mame.spec

    # executables
    install -d ${D}${bindir}
    install -pm 755 castool chdman floptool imgtool jedutil ldresample ldverify \
        mame nltool nlwav pngcmp romcmp unidasm ${D}${bindir}
    for tool in regrep split srcclean; do
        install -pm 755 $tool ${D}${bindir}/${BPN}-$tool
    done

    # misc
    install -d ${D}${datadir}/applications
    install -m 644 ${WORKDIR}/mame.desktop ${D}${datadir}/applications
    install -m 644 ${WORKDIR}/mame32k.desktop ${D}${datadir}/applications

    pushd artwork
        find -type d -exec install -d ${D}${datadir}/${BPN}/artwork/{} \;
        find -type f -exec install -pm 644 {} ${D}${datadir}/${BPN}/artwork/{} \;
    popd

    install -d ${D}${datadir}/${BPN}/ctrlr
    install -pm 644 ctrlr/*.cfg ${D}${datadir}/${BPN}/ctrlr

    install -d ${D}${datadir}/${BPN}/fonts
    install -m 644 uismall.bdf ${D}${datadir}/${BPN}/fonts

    install -d ${D}${datadir}/${BPN}/hash
    install -pm 644 hash/* ${D}${datadir}/${BPN}/hash

    install -d ${D}${datadir}/${BPN}/hlsl
    install -pm 644 hlsl/*.fx ${D}${datadir}/${BPN}/hlsl

    install -d ${D}${datadir}/${BPN}/keymaps
    install -pm 644 keymaps/* ${D}${datadir}/${BPN}/keymaps

    pushd language
        find -type d -exec install -d ${D}${datadir}/${BPN}/language/{} \;
        find -type f -name \*.mo -exec install -pm 644 {} ${D}${datadir}/${BPN}/language/{} \;
    popd

    pushd plugins
        find -type d -exec install -d ${D}${datadir}/${BPN}/plugins/{} \;
        find -type f -exec install -pm 644 {} ${D}${datadir}/${BPN}/plugins/{} \;
    popd

    install -d ${D}${datadir}/${BPN}/shader
    pushd src/osd/modules/opengl
        install -pm 644 shader/*.?sh ${D}${datadir}/${BPN}/shader
    popd

    pushd docs/man
        install -d ${D}${mandir}/man1
        install -pm 644 castool.1 chdman.1 imgtool.1 floptool.1 jedutil.1 ldresample.1 \
                        ldverify.1 romcmp.1 ${D}${mandir}/man1
        install -d ${D}${mandir}/man6
        install -pm 644 mame.6 mess.6 ${D}${mandir}/man6
    popd

    # install paths where user can add downloaded files
    for folder in roms samples; do
        install -d ${D}${sysconfdir}/skel/Emulators/${BPN}/$folder
    done
    echo "Store your downloaded files in subfolders" >> ${D}${sysconfdir}/skel/Emulators/${BPN}/Readme

    # install paths for created data
    for folder in cfg comments diff history inp nvram snap state; do
        install -d ${D}${sysconfdir}/skel/.${BPN}/$folder
    done

    # user config
    install -d ${D}${sysconfdir}/skel/.config/${BPN}/ini

    # Create ini file
    cat > ${WORKDIR}/${BPN}.ini << EOF
# Define source paths
artpath            ${datadir}/${BPN}/artwork
#bgfx_path          ${datadir}/${BPN}/bgfx
#cheatpath          ${datadir}/${BPN}/cheat
#crosshairpath      ${datadir}/${BPN}/crosshair
ctrlrpath          ${datadir}/${BPN}/ctrlr
fontpath           ${datadir}/${BPN}/fonts
hashpath           ${datadir}/${BPN}/hash
languagepath       ${datadir}/${BPN}/language
pluginspath        ${datadir}/${BPN}/plugins
# Have these per user for simple download/install
rompath            \$HOME/Emulators/${BPN}/roms
samplepath         \$HOME/Emulators/${BPN}/samples

# Allow user to override ini settings
inipath            \$HOME/.config/${BPN}/ini;${sysconfdir}/${BPN}

# Set paths for local storage
cfg_directory      \$HOME/.${BPN}/cfg
comment_directory  \$HOME/.${BPN}/comments
diff_directory     \$HOME/.${BPN}/diff
historypath        \$HOME/.${BPN}/history
input_directory    \$HOME/.${BPN}/inp
nvram_directory    \$HOME/.${BPN}/nvram
snapshot_directory \$HOME/.${BPN}/snap
state_directory    \$HOME/.${BPN}/state
EOF
    install -d ${D}${sysconfdir}/${BPN}
    install -m 644 ${WORKDIR}/${BPN}.ini ${D}${sysconfdir}/${BPN}

    # Logo
    install -d ${D}${datadir}/pixmaps
    install -m 644 ${S}/docs/source/images/MAMElogo.svg ${D}${datadir}/pixmaps

    # cleanup
    find ${D}${datadir}/${BPN} -name LICENSE -exec rm {} \;
    find ${D}${datadir}/${BPN} -name README.md -exec rm {} \;
}

PACKAGES =+ "${PN}-tools"
FILES:${PN}-tools = " \
    ${bindir}/castool \
    ${bindir}/chdman \
    ${bindir}/floptool \
    ${bindir}/imgtool \
    ${bindir}/jedutil \
    ${bindir}/ldresample \
    ${bindir}/ldverify \
    ${bindir}/nltool \
    ${bindir}/nlwav \
    ${bindir}/pngcmp \
    ${bindir}/${BPN}-regrep \
    ${bindir}/romcmp \
    ${bindir}/${BPN}-split \
    ${bindir}/${BPN}-src2html \
    ${bindir}/${BPN}-srcclean \
    ${bindir}/unidasm \
"
