SUMMARY = "Open-source 6502 cross assembler"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=1f67e705f4eb5064027b8c40ccfdb43e"

# They move old versions to a different folder
MIRRORS =+ "http://www.floodgap.com/retrotech/xa/dists/ http://www.floodgap.com/retrotech/xa/dists/unsupported/"

SRC_URI = " \
    http://www.floodgap.com/retrotech/xa/dists/${BPN}-${PV}.tar.gz \
    file://0001-Align-Makefiles-for-cross-compiling.patch \
"
SRC_URI[md5sum] = "72cbd5619f75b0c9839b90c41d0f9ea6"
SRC_URI[sha256sum] = "32f2164c99e305218e992970856dd8e2309b5cb6ac4758d7b2afe3bfebc9012d"

BBCLASSEXTEND = "native"

do_install() {
    oe_runmake DESTDIR=${D} PREFIX=${prefix} install
}
