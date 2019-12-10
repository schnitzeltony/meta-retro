SUMMARY = "Open-source 6502 cross assembler"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=1f67e705f4eb5064027b8c40ccfdb43e"

# They move old versions to a different folder
MIRRORS =+ "http://www.floodgap.com/retrotech/xa/dists/ http://www.floodgap.com/retrotech/xa/dists/unsupported/"

SRC_URI = " \
    http://www.floodgap.com/retrotech/xa/dists/${BPN}-${PV}.tar.gz \
    file://0001-Align-Makefiles-for-cross-compiling.patch \
"
SRC_URI[md5sum] = "14265129071922dd964d89599775d13e"
SRC_URI[sha256sum] = "867b5b26b6524be8bcfbad8820ab3efe422b3e0cc9775dcb743284778868ba78"

BBCLASSEXTEND = "native"

do_install() {
    oe_runmake DESTDIR=${D} PREFIX=${prefix} install
}
