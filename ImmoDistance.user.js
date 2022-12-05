// ==UserScript==
// @name         Zeige ÖPNV-Verbindung zu Ziel in Immoscout24
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       Simon Stratmann
// @match        https://www.immobilienscout24.de/Suche/*
// @icon         https://www.google.com/s2/favicons?sz=64&domain=immobilienscout24.de
// @grant        none
// ==/UserScript==

(function() {
    'use strict';

     var executed = false;
        let observer = new MutationObserver(function (mutations) {
            console.log("ready");

            $( ".result-list-entry__data" ).each(function( index ) {
                //console.log( $( this ).text() );
                let addressElement = $(this).find(".result-list-entry__address");
                let address = addressElement.text();

                if (address.search(/\d+ ?\w?,/gm) > -1) {
                    $.get(`http://127.0.0.1:8080/directions?from=${address}`, function(data) {
                        $(`<div><br>${data.transitHtml} (${data.transitMinutes} min)<br>`+
                          `<img src="https://maps.gstatic.com/consumer/images/icons/1x/directions_bike_grey800_24dp.png" width="15" height="15">: ${data.bikeMeters} m - ${data.bikeMinutes} min</div>`

                         )
                            .insertAfter( addressElement )
                    });


                }

                executed  = true;
            });
            if (executed)
                observer.disconnect();


        });
        let target = document.querySelector("#listings");
        observer.observe(target, {childList: true, attributes: true, subtree: true, attributeFilter: ['class']});


})();
