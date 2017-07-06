// index.js

import React, { Component } from 'react'
import { Dimensions, StyleSheet, View } from 'react-native'

import RTMPStreamingView from './RTMPStreamingView'

export default class SampleApp extends Component {
  render() {
    return (
      <View style={styles.container}>
        <RTMPStreamingView style={StyleSheet.absoluteFill}/>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: StyleSheet.absoluteFillObject
})
