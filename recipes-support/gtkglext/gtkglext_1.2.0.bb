SUMMARY = "OpenGL Extension to GTK"
LICENSE = "GPLv2 | LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://COPYING.LIB;md5=bbb461211a33b134d42ed5ee802b37ff"

inherit gnomebase pkgconfig distro_features_check

REQUIRED_DISTRO_FEATURES = "x11 opengl"

DEPENDS += "gtk+ glib-2.0-native pangox-compat libglu libxmu"

SRC_URI += " \
    file://0001-gtkglwidget-various-fixes.patch \
    file://0002-Fix-variable-name-conflict.patch \
"
SRC_URI[archive.md5sum] = "ed7ba24ce06a8630c07f2d0ee5f04ab4"
SRC_URI[archive.sha256sum] = "16bd736074f6b14180f206b7e91263fc721b49912ea3258ab5f094cfa5497f51"
GNOME_COMPRESS_TYPE="bz2"

FILES_${PN}-dev += "${libdir}/gtkglext-1.0"
