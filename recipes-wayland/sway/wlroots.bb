SUMMARY = "Modules for building a Wayland compositor"
HOMEPAGE = "https://github.com/swaywm/wlroots" 
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7578fad101710ea2d289ff5411f1b818" 

DEPENDS = " \
    wayland-native \
    wayland \
    wayland-protocols \
    virtual/egl \
    virtual/libgles2 \
    libdrm \
    virtual/libgbm \
    libinput \
    libxkbcommon \
    udev \
    pixman \
"

inherit meson features_check

REQUIRED_DISTRO_FEATURES = "wayland"

SRC_URI = " \
    git://github.com/swaywm/${BPN}.git \
    file://0001-Adjust-meson.build-to-meson-0.54.patch \
"
SRCREV = "238d1c078fb03338e9f271d98f7bf6b1fc399285"
PV = "0.12.0"
S = "${WORKDIR}/git"
