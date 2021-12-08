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
    git://github.com/joncampbell123/dosbox-x.git;branch=master;protocol=https \
    file://0001-use-pkgconfig-to-find-sdl2.patch \
    file://0002-Enable-unaligned-memory-based-on-recipe-s-suggestion.patch \
"
SRCREV = "71ebd3b0df3362085ba04e908d2630f951f8733d"
PV = "0.83.20"
S = "${WORKDIR}/git"

EXTRA_OECONF = " \
    --disable-sdl \
    --disable-sdltest \
    --enable-sdl2 \
    --disable-sdl2test \
    --disable-alsatest \
"

# sorry - but it does not make sense
COMPATIBLE_HOST:armv4 = 'null'
COMPATIBLE_HOST:armv5 = 'null'
COMPATIBLE_HOST:armv6 = 'null'

FILES:${PN} += "${datadir}/metainfo"
