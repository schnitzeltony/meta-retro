SUMMARY = "DOSBox-X fork of the DOSBox project"
HOMEPAGE = "https://dosbox-x.com/" 

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b5d36d7c1f35e9597b9843b9bedb6667" 

DEPENDS = " \
    libsdl2 \
    libsdl2-net \
    libpcap \
    libxkbfile \
    libpng \
    alsa-lib \
    fluidsynth \
"

inherit autotools-brokensep pkgconfig dos2unix gtk-icon-cache features_check

REQUIRED_DISTRO_FEATURES = "x11 opengl"

SRC_URI = " \
    git://github.com/joncampbell123/dosbox-x.git \
    file://0001-use-pkgconfig-to-find-sdl2.patch \
    file://0002-Enable-unaligned-memory-based-on-recipe-s-suggestion.patch \
    file://0003-menu-Fix-segfaults-during-menu-creation-in-some-envi.patch \
    file://0004-Treat-all-arm-hosts-as-armv7.patch \
    file://dosbox-x.desktop \
"
SRCREV = "0f1435e18d06ac955f26211541a9556bac759ae2"
PV = "0.83.6"
S = "${WORKDIR}/git"

EXTRA_OECONF = " \
    --disable-sdl \
    --disable-sdltest \
    --enable-sdl2 \
    --disable-sdl2test \
    --disable-alsatest \
"

# sorry - but it does not make sense
COMPATIBLE_HOST_armv4 = 'null'
COMPATIBLE_HOST_armv5 = 'null'
COMPATIBLE_HOST_armv6 = 'null'

do_install_append() {
	install -d ${D}/${datadir}/applications
	install -m 0644 ${WORKDIR}/*.desktop ${D}/${datadir}/applications
}

FILES_${PN} += "${datadir}/metainfo"
