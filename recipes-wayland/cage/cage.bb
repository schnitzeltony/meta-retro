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

inherit meson features_check

REQUIRED_DISTRO_FEATURES = "wayland"

SRC_URI = "git://github.com/Hjdskes/${BPN}.git"
SRCREV = "d09739373288adef901cdd58c983c0d02302932c"
PV = "0.1.2.1"
S = "${WORKDIR}/git"

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES','x11','xwayland','',d)}"
PACKAGECONFIG[xwayland] = "-Dxwayland=true,-Dxwayland=false,xserver-xorg,xserver-xorg-xwayland"

