SUMMARY = "D2X-Rebirth is a Source Port of Descent 2 Game"
HOMEPAGE = "http://www.dxx-rebirth.com/"
SECTION = "games"
LICENSE = "DXX-Rebirth"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=b49c5b969b5077f90bcbe744d1c84308"

DEPENDS = "python-scons-native libsdl libsdl-mixer physfs"

SRC_URI = " \
    http://www.dxx-rebirth.com/download/dxx/${BPN}_${PV}-src.tar.gz \
    http://www.dxx-rebirth.com/download/dxx/content/descent2-pc-demo.zip;name=data \
"
SRC_URI[md5sum] = "584187fdd922d732e47a64451085eaca"
SRC_URI[sha256sum] = "a8ebb1a17302380e933e9a4a9208290acd5e21054737c090013b30a7c240af22"
SRC_URI[data.md5sum] = "bdc62476e4b9ffe5381651c2a78963d9"
SRC_URI[data.sha256sum] = "b842cf983d0f393cede5cb6703186ec6eedac9bc5732dc577966f6f3122b9130"

S = "${WORKDIR}/${BPN}_${PV}-src"

do_compile() {
    scons opengl=0 prefix=${D}${prefix} sharepath=${datadir}/${BPN}
}

do_install() {
    install -d ${D}${bindir}
    install ${BPN} ${D}${bindir}

    install -d ${D}${datadir}/applications
    install ${BPN}.desktop ${D}${datadir}/applications

    install -d ${D}${datadir}/pixmaps
    install ${BPN}.xpm ${D}${datadir}/pixmaps

    install -d ${D}${datadir}/${BPN}
    install ../descent.hog ../descent.pig ${D}${datadir}/${BPN}
}

