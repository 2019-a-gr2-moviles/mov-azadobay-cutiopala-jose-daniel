/**
 * Libro.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    titulo: {
      type: 'string',
      required: true
    },

    autor: {
      type: 'string',
      required: true
    },

    editorial: {
      type: 'string',
      required: true
    },

    numEdicion: {
      type: 'string',
      required
    },

    stock: {
      type: 'number',
      required: true
    }

  },

};

