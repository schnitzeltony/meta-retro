SUMMARY = "Cage: a Wayland kiosk"
HOMEPAGE = "https://www.hjdskes.nl/projects/cage/" 
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3d06ce025701c9a0b391f15902ce8ed" 

DEPENDS = " \
    wayland-native \
    wayland \
    wayland-protocols \
    wlroots \
    pixman \
    libxkbcommon \
"

inherit meson pkgconfig features_check

REQUIRED_DISTRO_FEATURES = "wayland"

SRC_URI = "git://github.com/Hjdskes/${BPN}.git;branch=master;protocol=https"
SRCREV = "646b3e80b45a011676eb4190652b8c506d56e7a4"
PV = "0.1.4"
S = "${WORKDIR}/git"

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES','x11','xwayland','',d)}"
PACKAGECONFIG[xwayland] = "-Dxwayland=true,-Dxwayland=false,xserver-xorg,xwayland"

