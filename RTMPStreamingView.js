import React, { Component } from 'react'
import { requireNativeComponent, View, Text, TouchableOpacity, Image, StyleSheet } from 'react-native'

var iface = {
  name: 'RTMPStreamingView',
  propTypes: {
    ...View.propTypes
  },
};

const RTMPStreamingView = requireNativeComponent('RTMPStreamingView', iface)

export default RTMPStreamingView

const styles = StyleSheet.create({
  text:{
    color: 'green'
  }
})
