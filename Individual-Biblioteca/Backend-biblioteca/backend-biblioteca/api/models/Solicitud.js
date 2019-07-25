/**
 * Solicitud.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    fecha: {
      type: 'string',
      required: true
    },

    prestamo: {
      type: 'boolean',
      defaultsTo: true

    },

    idLibro: {
      model: 'libro',
    },

    idLector: {
      model: 'lector'
    }


    

  },

};

