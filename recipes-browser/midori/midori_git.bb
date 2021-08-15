SUMMARY = "A lightweight web browser"
HOMEPAGE = "https://www.midori-browser.org/"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

DEPENDS = " \
    intltool-native \
    gcr \
    webkitgtk \
    json-glib \
    libarchive \
    libsoup-2.4 \
    libpeas \
    sqlite \
"

inherit cmake pkgconfig gtk-icon-cache gettext vala python3native features_check gobject-introspection mime-xdg

ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"

# Midori requires gobject-introspection to build. Check below is not the whole
# story: There are some extra machine-specific disable entries in webkitgtk
# recipe and there is nothing we can do to check here...
python () {
    if d.getVar('GI_DATA_ENABLED') != 'True':
        raise bb.parse.SkipRecipe('Midori needs gobject-introspection but it is either disabled or machine does not support it!' )
}

SRC_URI = " \
    git://github.com/midori-browser/core.git \
    file://0001-Do-no-search-for-cross-g-ir-compiler-but-g-ir-compil.patch \
"
SRCREV = "f6b3b1e030db7eb2de7faf73ec4ccc18949b668d"
PV = "9.0"
S = "${WORKDIR}/git"

EXTRA_OECMAKE = " \
    -DCMAKE_INSTALL_PREFIX=${prefix} \
    -DCMAKE_INSTALL_SYSCONFDIR=/etc \
"

do_configure:prepend() {
    export VALAC=${STAGING_BINDIR_NATIVE}/valac
}

RRECOMMENDS:${PN} += " \
    glib-networking ca-certificates \
    adwaita-icon-theme \
"

# No project but oe packs gstreamer plugins that fine as we do. Cannot say
# which plugins are # really necessary so use gstreamer1.0-plugins-*-meta.
# These are necessary to make video streaming work - see [1]
# [1] https://github.com/midori-browser/core/issues/329
RRECOMMENDS:${PN} += " \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    gstreamer1.0-libav \
"

FILES:${PN} += "${datadir}/metainfo"
