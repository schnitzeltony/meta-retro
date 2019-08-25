SUMMARY = "Compatibility library for pangox"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7"

inherit gnomebase pkgconfig

DEPENDS += "pango"

SRC_URI[archive.md5sum] = "7bcbd0187f03e1e27af9a81e07249c33"
SRC_URI[archive.sha256sum] = "552092b3b6c23f47f4beee05495d0f9a153781f62a1c4b7ec53857a37dfce046"
