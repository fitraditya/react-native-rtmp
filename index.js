// index.js

import React, { Component, PropTypes } from 'react'
import { Dimensions, View, Text, Image, StyleSheet, TouchableOpacity } from 'react-native'

import RTMPStreamingView from './RTMPStreamingView'

export default class SampleApp extends Component {
  static defaultProps = {
    settingButtonImage: {require('./images/settingButtonImage.png')},
    switchButtonImage: {require('./images/switchButtonImage.png')},
    publishButtonImage: {require('./images/publishButtonImage.png')},
  };

  constructor(props) {
    super(props);
  }

  onSettingButtonPressed() {
    //
  }

  onSwitchButtonPressed() {
    //
  }

  onPublishButtonPressed() {
    //
  }

  renderTopButtons() {
    return (
      <View style={styles.topButtons}>
        {this.renderSettingButton()}
        {this.renderSwitchButton()}
      </View>
    )
  }

  renderBottomButtons() {
    return (
      <View style={styles.bottomButtons}>
        {this.renderPublishButton()}
      </View>
    )
  }

  renderSettingButton() {
    return (
      <TouchableOpacity style={{ paddingHorizontal: 15 }} onPress={()=>this.onSettingButtonPressed()}>
        <Image style={{ flex: 1, justifyContent: 'center' }}
          source={this.prop.settingButtonImage}
          resizeMode={Image.resizeMode.contain}/>
      </TouchableOpacity>
    )
  }

  renderSwitchButton() {
    return (
      <TouchableOpacity style={{ paddingHorizontal: 15 }} onPress{()=>this.onSwitchButtonPressed()}>
        <Image style={{ flex: 1, justifyContent: 'center' }}
          source={this.props.switchButtonImage}
          resizeMode={Image.resizeMode.contain}/>
      </TouchableOpacity>
    )
  }

  renderPublishButton() {
    return (
      <View style={styles.publishButtonContainer}>
        <TouchableOpacity onPress={()=>this.onPublishButtonPressed()}>
          <Image style={styles.publishButton}
            source={this.props.publishButtonImage}
            resizeMode={'contain'}>
          </Image>
        </TouchableOpacity>
      </View >
    )
  }

  renderCamera() {
    return (
      //<View style={styles.container}>
        <RTMPStreamingView style={StyleSheet.absoluteFill}/>
      //</View>
    )
  }

  renderGap() {
    return (
      <View style={{flex: 10, flexDirection: 'column'}}/>
    );
  }

  render() {
    return (
      <View style={{ flex: 1, backgroundColor: 'transparent' }}>
        {this.renderCamera()}
        {this.renderTopButtons()}
        {this.renderGap()}
        {this.renderBottomButtons()}
      </View>
    );
  }
}

const { width, height } = Dimensions.get('window');

const styles = StyleSheet.create({
  topButtons: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingTop: 8,
    paddingBottom: 0
  },
  bottomButtons: {
    flex: 2,
    flexDirection: 'row',
    justifyContent: 'space-between',
    padding: 14
  },
  publishButtonContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  publishButton: {
    flex: 1,
    alignSelf: 'center',
    alignItems: 'center',
    justifyContent: 'center'
  },
  container: {
    width: width,
    height: height,
    position: 'absolute',
    top: 0,
    left: 0,
    flex: 10,
    flexDirection: 'column'
  },
  //container: StyleSheet.absoluteFillObject
})
