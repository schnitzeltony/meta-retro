SUMMARY = "Versatile Commodore Emulator"
HOMEPAGE = "http://vice-emu.sourceforge.net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://doc/html/plain/COPYING;md5=c93c0550bd3173f4504b2cbd8991e50b"

# Sources for c64 software:
# [1] ftp://arnold.c64.org/pub/games/

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/project/vice-emu/releases/${BPN}-${PV}.tar.gz \
    file://vice_64.desktop \
"
SRC_URI[md5sum] = "b017647a0c159bbe43cdb81762d1c577"
SRC_URI[sha256sum] = "ff8b8d5f0f497d1f8e75b95bbc4204993a789284a08a8a59ba727ad81dcace10"

inherit autotools pkgconfig

DEPENDS = "libxt libxmu libxaw libxpm libxv pulseaudio libav libsdl libpng jpeg giflib"

# some options autotools.bbclass adds by default cause configure to complain
# so (hack): copy from autotools.bbclass and remove unwanted
CONFIGUREOPTS = " \
    --build=${BUILD_SYS} \
    --host=${HOST_SYS} \
    --target=${TARGET_SYS} \
    --prefix=${prefix} \
    --exec_prefix=${exec_prefix} \
    --bindir=${bindir} \
    --sbindir=${sbindir} \
    --libexecdir=${libexecdir} \
    --datadir=${datadir} \
    --sysconfdir=${sysconfdir} \
    --sharedstatedir=${sharedstatedir} \
    --localstatedir=${localstatedir} \
    --libdir=${libdir} \
    --includedir=${includedir} \
    --oldincludedir=${oldincludedir} \
    --infodir=${infodir} \
    --mandir=${mandir} \
    ${CONFIGUREOPT_DEPTRACK} \
"

EXTRA_OECONF = "--disable-ffmpeg"

do_install_append() {
    install -d ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/vice_64.desktop ${D}/${datadir}/applications
}

FILES_${PN} += "${datadir}/icons"
