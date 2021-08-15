SUMMARY = "DOS/x86 emulator focusing on ease of use"
HOMEPAGE = "https://dosbox-staging.github.io/" 

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=a75e9ff85f9de2c690521c2b6ddd26cf" 

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
"

inherit meson gtk-icon-cache features_check

REQUIRED_DISTRO_FEATURES = "x11 opengl"

SRC_URI = " \
    git://github.com/dosbox-staging/dosbox-staging.git \
    file://0001-Fix-missing-include.patch \
"
SRCREV = "15a57e26c4d1123606a7ac455d2ccbce15e976b9"
PV = "0.77.0"
S = "${WORKDIR}/git"

DYNCORE ?= "-Ddynamic_core=dynrec"

EXTRA_OEMESON = " \
    -Duse_pcap=true \
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

