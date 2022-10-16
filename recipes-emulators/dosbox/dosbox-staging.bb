SUMMARY = "DOS/x86 emulator focusing on ease of use"
HOMEPAGE = "https://dosbox-staging.github.io/" 

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=ca8be14ecd86a8ae1155f8023c73bca6"

DEPENDS = " \
    libsdl2 \
    libsdl2-net \
    libpcap \
    libxkbfile \
    libpng \
    alsa-lib \
    fluidsynth \
    opusfile \
    munt \
    libslirp \
    iir1 \
"

inherit meson pkgconfig gtk-icon-cache features_check

REQUIRED_DISTRO_FEATURES = "x11 opengl"

SRC_URI = "git://github.com/dosbox-staging/dosbox-staging.git;branch=release/0.79.x;protocol=https"
SRCREV = "dd7f21809870cc998005b3b09a33f1c36bd764a6"
PV = "0.79.1"
S = "${WORKDIR}/git"

DYNCORE ?= "-Ddynamic_core=dynrec"

EXTRA_OEMESON = " \
    ${DYNCORE} \
"

do_install:append() {
    # avoid clashes
    mv ${D}${bindir}/dosbox ${D}${bindir}/dosbox-staging
    sed -i 's:Exec=dosbox:Exec=dosbox-staging:g' ${D}${datadir}/applications/dosbox-staging.desktop
}

# sorry - but it does not make sense
COMPATIBLE_HOST:armv4 = 'null'
COMPATIBLE_HOST:armv5 = 'null'
COMPATIBLE_HOST:armv6 = 'null'

FILES:${PN} += "${datadir}/metainfo"
FILES:${PN}-doc += "${datadir}/licenses"

