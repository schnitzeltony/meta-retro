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
    seatd \
"

inherit meson pkgconfig features_check

REQUIRED_DISTRO_FEATURES = "wayland opengl"

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES','x11','xwayland x11-backend','',d)}"
PACKAGECONFIG[xwayland] = "-Dxwayland=enabled,-Dxwayland=disabled,virtual/libx11 xwayland xcb-util-wm"
PACKAGECONFIG[x11-backend] = "-Dx11-backend=enabled,-Dx11-backend=disabled,virtual/libx11 xcb-util-renderutil"

SRC_URI = "git://github.com/swaywm/${BPN}.git;branch=0.14"
SRCREV = "49a574420138646845fb664862db03982cfd9f5b"
PV = "0.14.1"
S = "${WORKDIR}/git"
