SUMMARY = "Open-source 6502 cross assembler"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=1f67e705f4eb5064027b8c40ccfdb43e"

# They move old versions to a different folder
MIRRORS =+ "http://www.floodgap.com/retrotech/xa/dists/ http://www.floodgap.com/retrotech/xa/dists/unsupported/"

SRC_URI = " \
    http://www.floodgap.com/retrotech/xa/dists/${BPN}-${PV}.tar.gz \
    file://0001-Align-Makefiles-for-cross-compiling.patch \
"
SRC_URI[md5sum] = "f533c3d36fcedcbca3b61a90ded6f37f"
SRC_URI[sha256sum] = "8d3097d3b75adf4305d7d5c8e8f2568a7176cb348bcc50006cfc58378540c555"

BBCLASSEXTEND = "native"

do_install() {
    oe_runmake DESTDIR=${D} PREFIX=${prefix} install
}
